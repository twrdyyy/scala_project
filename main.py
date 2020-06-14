from server import OpenAIServer

game = 'CartPole-v1'
render = True

if __name__ == "__main__":
    server = OpenAIServer(env=game)
    server.run(render=render)
