/*
Reference for pseudocode help
https://www.baeldung.com/cs/networking-go-back-n-protocol
*/


import java.io.*;           //import input output package
import java.net.*;          //import networking package
import java.util.Scanner;   //import user input package, in this case used to create an object to read .txt file into
import java.util.Timer;     //inport timer package to create a timer to wiat for ack


class UDPClient             //create class UDPClient
{
    public static void main(String args[]) throws Exception
    {
        DatagramSocket clientSocket = new DatagramSocket();                     //constructs client socket
        InetAddress IPAddress = InetAddress.getByName("localhost");             //store ip address of local host
        
        File myObj = new File("data.txt");                                      //create file: myObj with data in data.txt
        Scanner myReader = new Scanner(myObj);                                  //create scanner:myReader and read in data from myObj
        String sentence = myReader.nextLine();                                  //create string: sentance and read in data from myReader
        myReader.close();                                                       //close myReader

        int sendBase = 0;                                                       //sendBase is the starting number for packet window
        int nextSeqNum = 0;                                                     //nextSeqNum is sequence number to be attached to data packet
        int windowSize = 1;                                                     //windowSize is N, intialised to 1 for use as initial stop and wait, changed to 5 once first ack received
        
        while (true){                                                           //forever loop
            if (nextSeqNum < (sendBase + windowSize)) {                         //checks if should run based on window size and received number of packets sent
                String SNsentence = nextSeqNum + sentence;                      //append sequence number to front of sentence, new string SNsentence
                byte[] sendData = SNsentence.getBytes();                        //create byte array containing SNsentence  
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876); //create new datagram: sendPacket and package with sendData length, IPaddress of sender and port number
                clientSocket.send(sendPacket);                                  //send the packet
                nextSeqNum++;                                                   //increment the nextSeqNum by 1
            }
        
            byte[] receiveData = new byte[1024];                                //create new byte array: receiveData containing 1024
            DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);  //create packet to receive datagram
            clientSocket.receive(receivePacket);                                //receive packet from server the data received is the seq number of the last received packet 
            String rec = new String(receivePacket.getData());                   //convert data from byte array to String: rec
            char ch = rec.charAt(0);                                            //convert to char: ch
            int recSN = Character.getNumericValue(ch);                          //convert to int: recSN for use in later computations
            
            if(recSN == 0) {                                                    //to switch from stop and wait to go-back-n I change the window size from 1 to 5 as per specs
                windowSize = 5;                                                 //if recSN if doesn't run, meaning either ack stop and wait hasn't received ack, or has already gone past that step
            }
            
            Timer timer = new Timer();                                          //create a new Timer: timer
            if (recSN == nextSeqNum) {                                          //if ack matches correct sequence number the run
                sendBase++;                                                     //increment sendBase by 1
                if (sendBase==nextSeqNum) {                                     //sendBase should now match nextSeqNum if all connections and packets correct
                    timer.cancel();                                             //stop timer so program moves without delay
                }
                else {                                                          //if recSN != nextSeqNum
                    timer.wait(1000);                                           //wait 1 sec to see if ack received
                }
            if (timer.equals(0)) {                                              //if timer has run down to 0
                for(int i = sendBase; i<nextSeqNum; i++){                       //for loop to resend all datagrams from sendBase to current expected sequence number
                    String SNsentence = i + sentence;                           //create string SNsentance which adds current sequence numbers:i
                    byte[] sendData = SNsentence.getBytes();                    //create byte array containing SNsentence 
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876); //create new datagram: sendPacket and package with sendData length, IPaddress of sender and port number
                    clientSocket.send(sendPacket);                              //send the packet
                    } 
                }
            }
        }
    }
}