class Dog:
    def __init__(self, breed, sex, age):
        self.breed = breed
        self.sex = sex
        self.age = age

    @classmethod
    def bark(cls):
        print("WOOF")

    @staticmethod
    def stuff():
        print("thing1")

    @staticmethod
    def stuff2():
        print("thing2")


lab = Dog('Labrador', 'Dog', 2)

print(lab.breed)
print(lab.sex)
print(lab.age)
print(lab)
Dog.bark()
Dog.stuff()
Dog.stuff2()
