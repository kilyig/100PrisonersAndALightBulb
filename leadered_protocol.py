import random, argparse
from decimal import *

parser = argparse.ArgumentParser()
parser.add_argument("-p", "--Prisoner_Count", help="number of prisoners", type=int)
parser.add_argument("-i", "--Iterations", help="number of iterations", type=int)

args = parser.parse_args()

p = args.Prisoner_Count
i = args.Iterations

sum = 0
for x in range(i):
    sent_signal = [False] * (p - 1)
    interrogation_count = 0
    received = 0
    lightbulb_on = False
    if(p == 1):
        interrogation_count += 1
    while received < (p - 1):
        curr = random.randint(0, p - 1)
        interrogation_count += 1
        if(curr == p - 1):
            if(lightbulb_on):
                received += 1
                lightbulb_on = False
        elif(not lightbulb_on and not sent_signal[curr]):
            lightbulb_on = True
            sent_signal[curr] = True
        
    sum += interrogation_count
    
average = Decimal(sum) / Decimal(i)

print("Prisoner count:", p)
print("Number of iterations:", i)
print("Average:", average)
