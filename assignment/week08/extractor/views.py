from django.shortcuts import render
import random

# Create your views here.
def index(request):
    return render(request, 'input.html')

def generator(request):
    game_number = int(request.GET.get('game'))

    lotto_numbers = []
    for _ in range(game_number):
        lotto_number = []
        for _ in range(7):
            lotto_number.append(random.randint(1, 45))
        lotto_numbers.append(lotto_number)

    return render(request, 'output.html', {'game_number': game_number, 'lotto_numbers': lotto_numbers})
