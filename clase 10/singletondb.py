from mysql.connector import connect, Error
import mysql




class DBSingleton(object):

    __instance = None

    __host = None
    __user = None
    __password = None
    __database = None

    __session = None
    __connection = None

    def __new__(cls, *args, **kwargs):
        if not cls.__instance:
            cls.__instance = super(DBSingleton, cls).__new__(cls, *args, **kwargs)
        return cls.__instance

    def __init__(self, host='localhost', user='root', password='password', database='prueba'):
        self.__host = host
        self.__user = user
        self.__password = password
        self.__database = database

    #Open connection with database
    def _open(self):
        try:
            cnx = mysql.connector.connect(host=self.__host, user=self.__user, password=self.__password,
                                          database=self.__database)
            self.__connection = cnx
            self.__session = cnx.cursor()
        except mysql.connector.Error as err:
            if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
                print ('Something is wrong with your user name or password')
            elif err.errno == errorcode.ER_BAD_DB_ERROR:
                print ('Database does not exists')
            else:
                print (err)

    def _close(self):
        self.__session.close()
        self.__connection.close()

    
    def get_cities(self, *args):
        result = None
        query = "SELECT * FROM city "
        self._open()
        with self.__connection.cursor() as cursor:
                cursor.execute(query)
                result = cursor.fetchall()
        return result

   






