class Implementation:
    def f(self):
        print("f")
        return "Implementation.f()"
    def g(self):
        print("g")
        return "Implementation.g()"
    def h(self):
        print("h")
        return "Implementation.h()"

class Proxy:
    def __init__(self):
        self.__implementation = Implementation()

    def f(self): return self.__implementation.f()
    def g(self): return self.__implementation.g()
    def h(self): return self.__implementation.h()

