data = input()
open = 0
result = 0
pre = ""

for i in data:
    if(i == '('):
        open += 1
        pre = '('
    else:
        if(pre == '('): #레이저 맞으면
            open -= 1
            result += open
        else: #레이저 아니면
            open -= 1
            result += 1
        pre = ')'
print(result)