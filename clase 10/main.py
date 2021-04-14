from flask import Flask
from flask import jsonify
from mysql.connector import connect, Error
from singletondb import DBSingleton
from proxy import Proxy


def create_app():
    app = Flask(__name__)
    return app


app = create_app()


@app.route('/cities', methods=['GET','POST'])
def get_cities_no_pattern():
    try:
        with connect(
            host="localhost",
            user="root",
            password="password",
            database="prueba"
        ) as connection:
            select_movies_query = "SELECT * FROM city"
            with connection.cursor() as cursor:
                cursor.execute(select_movies_query)
                result = cursor.fetchall()
                return jsonify(result)
    except Error as e:
        print(e)
        response = {'message': 'fail'}
        return jsonify(response)



@app.route('/singleton_cities', methods=['GET','POST'])
def get_cities_singleton():
    
    s = DBSingleton()
    print(s) 
    response = s.get_cities()
    
    return jsonify(response)
   
@app.route('/proxy', methods=['GET','POST'])
def get_proxy():
    p = Proxy()
    result = p.f()
    response = {"message":result}
    return jsonify(response)
   