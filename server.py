import socket
from time import sleep
import gym

host = ''
port = 50000
backlog = 5
size = 1024
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((host, port))
s.listen(backlog)

env = gym.make('CartPole-v0')
env.reset()

for _ in range(1000):
    while 1:
        client, address = s.accept()
        data = client.recv(size)
        if data:
            print(data.decode("utf-8"))
            client.send(data)
            _ = client.recv(size)
            client.close()
            break
        client.close()
    env.render()
    if data.decode("utf-8") == "random":
        env.step(env.action_space.sample())
    sleep(1)
    break

env.close()



