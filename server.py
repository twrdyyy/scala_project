import socket
from time import sleep
import gym

class OpenAIServer:
    def __init__(self,
                 env : str,
                 server_host : str = '',
                 server_port : int =50000,
                 backlog : int = 5,
                 msg_size : int = 1024):
        """
        Initialize server variables

        Args:
        @game        (str) : open-ai gym environment name
        @server_host (str) : server host
        @server_port (int) : server port
        @backlog     (int) : server log depth
        @msg_size    (int) : message size
        """

        self.server_host = server_host
        self.server_port = server_port
        self.backlog = backlog
        self.msg_size = msg_size
        self.game = env
        self.sock = None
        self.env = None


    def run(self,
            render : bool = False):
        """
        Start open-ai server and wait for start response from client than
        evaluate response render

        Args:
        @redner (bool) : decide if server should render open-ai gym or not
        """

        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.sock.bind((self.server_host, self.server_port))
        self.sock.listen(self.backlog)

        self.env = gym.make(self.game)
        self.env.reset()

        action = "start"

        while True:

            if render:
                self.env.render()

            if action == "end":
                self.env.close()
                break
            elif action == "start":
                observation = self.env.reset()
                done = False
                env_data = " ".join([f"{x:.6f}" for x in observation]) + ";0;0"
            else:
                action = int(action)
                observation, reward, done, _ = self.env.step(action)
                env_data = " ".join([f"{x:.6f}" for x in observation]) + ";" + f"{reward:.2f}" + ";" + str(int(done))

            if done:
                self.env.reset()

            while 1:
                client, address = self.sock.accept()
                client.send(env_data.encode())
                data = client.recv(self.msg_size)
                if data:
                    client.close()
                    break

            action = str(data.decode("utf-8"))

        self.env.close()

