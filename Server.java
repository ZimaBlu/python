/*
Reference for pseudocode help
baeldung.com 'Go-Back-N Protocol', 2021. [Online]. Available: https://www.baeldung.com/cs/networking-go-back-n-protocol [Accessed: Oct 2021].
*/

import java.net.*; //java's networking package

class UDPServer //created class named UDPServer
{
    public static void main(String args[]) throws Exception 
    {
        DatagramSocket serverSocket = new DatagramSocket(9876);         //constructs server socket at 9876
        byte[] receiveData = new byte[1024];                            //create byte array to receive data & intiialises with '1024'
        byte[] sendData;                                                //create array to send data
        
        int nextSeqNum = 0;                                             //nextSeqNum will hold the value of the sequence number the server expects, used for checking
        while(true) {                                                   //forever loop
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); //create a datagram packet to take the data and the length of the packet received
            serverSocket.receive(receivePacket);                        //receive the data packet  
            InetAddress IPAddress = receivePacket.getAddress();         //store IP Address of packet received
            int port = receivePacket.getPort();                         //store port number used to receive
            
            String data = new String(receivePacket.getData());          //copy data from datapacket and store as string: data
            char ch = data.charAt(0);                                   //copy first char of string to ch
            int SNC = Character.getNumericValue(ch);                    //convert ch char to int for comparison use. This is the sequence numebr of the received datagram
        
            
            if (SNC == nextSeqNum) {                                    //check SNC(sequence number received) is identical to nextSeqNum(expected sequence number). If it is then receive datagram, if not disgard and send repeat request
                data = data.substring(1);                               //remove sequence number from data 
                System.out.println(data);                               //send to application layer, in this case print data to screen
                
                String NSNStr = Integer.toString(nextSeqNum);           //convert nextSeqNum to string: NSNStr
                sendData = NSNStr.getBytes();                           //convert NSNStr to bytes for sending
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port); //create sendPacket datagram for sending with sendData
                serverSocket.send(sendPacket);                          //send the packet
                
                nextSeqNum++;                                           //increase the nextSeqNum by 1
            }
            else {                                                      //if nextSeqNum != SNC, this section will alert client that packet not received, client will then resend
                nextSeqNum--;                                           //decrease value of nextSeqNum by 1
                String NSNStr = Integer.toString(nextSeqNum);           //convert nextSeqNum to String NSNStr
                sendData = NSNStr.getBytes();                           //convert to bytes and store in sendData array
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port); //create sendPacket datagram for sending with sendData
                serverSocket.send(sendPacket);                          //send datagram
            }
        }
    }
}  