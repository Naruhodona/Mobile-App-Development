from flask import Flask, jsonify, request
from flask_cors import CORS
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy import desc

app = Flask(__name__)
CORS(app)

app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:@localhost/task_inventory'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)


class Tasks(db.Model):
    __tablename__ = 'tasks'
    task_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    title = db.Column(db.String(255), nullable=False)
    description = db.Column(db.String(255), nullable=False)
    category = db.Column(db.String(255), nullable=False)
    created_time = db.Column(db.String(255), nullable=False)
    finished_time = db.Column(db.String(255), nullable=False)
    duration = db.Column(db.String(255), nullable=False)
    status = db.Column(db.String(255), nullable=False)

    def to_dict(self):
        return {'id':self.task_id,'title': self.title, 'description': self.description, 'category': self.category, 
        'created_time': self.created_time, 'finished_time': self.finished_time, 'duration': self.duration, 'status':self.status}

@app.route('/')
def home():
    return jsonify({'message': 'Hey'})
# add data
@app.route('/tasks', methods=['POST'])
def add_task():
    new_task = request.get_json()
    print(new_task)
    new_task_data = Tasks(title=new_task['title'], description=new_task['description'], category=new_task['category'], 
    created_time=new_task['created_time'],status=new_task['status'],finished_time=new_task['finished_time'],duration=new_task['duration'])
    db.session.add(new_task_data)
    db.session.commit()
    return jsonify({'message': 'Task added successfully'})


# get data (to show)
@app.route('/tasks', methods=['GET'])
def get_task():
    status = request.args.get('status')
    category = request.args.get('category')
    print('$$$$$$$$$$$$$$$$$$$$$$$$')
    if status and category:
        tasks = Tasks.query.filter_by(status = status, category = category)
    else:
        tasks = Tasks.query.all()
    task_list = [data.to_dict() for data in tasks]
    return jsonify(task_list)

# delete data?
@app.route('/tasks/<int:id>', methods=['DELETE'])
def delete_task(id):
    task_data = Tasks.query.get(id)
    if not task_data:
        return jsonify({'message': 'Task not found'}), 404

    db.session.delete(task_data)
    db.session.commit()
    return jsonify({'message': 'Task data deleted successfully'})

# filtered get: status == all, status == new, status == in progress, status == done, status == all AND category ==
@app.route('/tasks/<int:id>', methods=['GET'])
def get_task_data_by_id(id):
    task_data = Tasks.query.get(id)
    if task_data:
        return jsonify(task_data.to_dict())
    return jsonify({'message': 'Task data not found'}), 404

# move status
@app.route('/task_data/<int:id>', methods=['PUT'])
def update_task_data(id):
    task_data = Tasks.query.get(id)
    if not task_data:
        return jsonify({'message': 'Task data not found'}), 404

    data = request.get_json()
    if (data["status"] == "done"):
        task_data.finished_time = data['finished_time']
        task_data.duration = data['duration']
    task_data.status = data['status']
    db.session.commit()
    return jsonify({'message': 'Task data updated successfully'})



if __name__ == '__main__':
    # db.create_all()
    app.run(debug=True)