# How to run the Flask without any problem


### Import the database
Create a database named "task_inventory"

Import sql file named task_inventory.sql

### Activate virtual environment
Open Command Prompt terminal

Activate the virtual environment with \Scripts\activate command in "uasEnv" directory

After virtual environment activated, you can install requirement packages to run the flask by execute command "pip install -r requirements.txt". If it doesn't work, install these packages manually by execute command "pip install <package_name>":
1. flask
2. flask_mysqldb
3. flask_sqlalchemy
4. flask_cors

### Run the Flask
Run the flask by execute command "flask run"
