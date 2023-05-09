print("============================")
print('회원가입')
print("============================")

register = False

while not register:
    print('회원가입을 진행하겠습니까?')
    print('y: 진행      N: 취소')
    answer = input('>> ')
    answer = answer.lower()

    if answer == 'y':
        register = True
        print("============================")
        print("회원가입이 진행됩니다.")
        print("============================")
    elif answer == 'n':
        print("============================")
        print("회원가입이 취소됩니다.")
        print("============================")
    else:
        print('입력 값을 확인해주세요.')
        