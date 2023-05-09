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


user = []
while True:
    user = {}
    username = input('ID: ')
    while True:
        pwd = input('PWD: ')
        pwd2 = input('PWD 확인: ')
        if pwd == pwd2:
            break
        else:
            print('패스워드가 일치하지 않습니다.')
    name = input('이름: ')
    while True:
        birth = input('생년월일(6자리): ')
        if len(birth) == 6:
            break
        else:
            print('생성월일 입력값이 올바르지 않습니다.')
    email = input('이메일: ')