import re

Conventions = {
	'MOV': '0000',
	'AND': '0001',
	'OR': '0010',
	'ADD': '0011',
	'SUB': '0100',
	'MUL': '0101',
	'DIV': '0110',
	'ADDI': '0111',
	'SUBI': '1000',
	'MULI': '1001',
	'DIVI': '1010',
	'LD': '1011',
	'SD': '1100',
	'LUI': '1101',
	'ORI': '1110',
	'XOR': '1111',
	'$N0': '00',
	'$N1': '01',
	'$M0': '10',
	'$C0': '11'
}

Memory = {
	'0x0': '0000',
	'0x1': '0001',
	'0x2': '0010',
	'0x3': '0011',
	'0x4': '0100',
	'0x5': '0101',
	'0x6': '0110',
	'0x7': '0111',
	'0x8': '1000',
	'0x9': '1001',
	'0xA': '1010',
	'0xB': '1011',
	'0xC': '1100',
	'0xD': '1101',
	'0xE': '1110',
	'0xF': '1111',
}

Numbers = {
	'0': '00',
	'1': '01',
	'2': '10',
	'3': '11'
}

def getInstructions(instructionFile): #returns an array of instructions
    instructions = []
    instructionFile = open(instructionFile, 'r')
    while True:
        line = instructionFile.readline().strip('\n')
        if len(line) == 0:
            break
        instructions.append(line)
    instructionFile.close()
    return instructions

def toMachine(machineFile, instructions):
	machineFile = open(machineFile, 'w')
	for instruction in instructions:
		trimmed = re.split(', | ', instruction)
		machine = ""
		for x in range(0,len(trimmed)):
			if trimmed[x] in Memory:
				machine += "%s" % (Memory[trimmed[x]])
			elif trimmed[x] in Numbers:
				machine += "%s" % (Numbers[trimmed[x]])
			else:
				machine += "%s" % (Conventions[trimmed[x]])
		machine += "\n"
		machineFile.write(machine)
	machineFile.close()

def binToHex(item):
	hexDict = {10: 'a', 11: 'b', 12: 'c', 13: 'd', 14: 'e', 15: 'f'}
	sum = 0
	i = 3
	for ch in item:
		if ch == '1':
			sum += 2**i
		i -= 1
	if sum > 9:
		return hexDict[sum]
	return str(sum)

def machineToHex(machineFile, hexFile):
    hexa = open(hexFile, 'w')
    machine = open(machineFile)
    hexa.write("v2.0 raw\n") # Add header for Logisim ROM to hexa.txt
    adCount = 0
    for line in machine:
        line = '00' + line
        hexList = [line[0:4], line[4:8], line[8:12]]
        x = ""
        for item in hexList: 
            x += binToHex(item)
        # Each line with instruction in .rom has 8 addresses
        # 1. Take x write it in hexa.txt
        # 2. If adCount == 8 add \n, add space; else add ' '
        if adCount == 7:
            print(x, file=hexa) # Add \n after 8 instructions
            adCount = 0
        else:
            print(x, file=hexa, end=" ")
            adCount += 1
    machine.close()
    hexa.close()

instruction = 'instructions.txt'
machine = 'machine_language.txt'
hexa = 'hexa.txt'

instructions = getInstructions(instruction)
toMachine(machine, instructions)
machineToHex(machine, hexa)