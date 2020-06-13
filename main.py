from server import OpenAIServer

game = 'CartPole-v0'
render = False

if __name__ == "__main__":
    server = OpenAIServer(env=game)
    server.run(epochs=1000
               render=render)
