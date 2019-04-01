#include <bits/stdc++.h>
#include <string>
#include<iostream>
#include <iomanip>
#include<math.h>

using namespace std;


void toLower(string& instruction) // Function for converting incoming stringstream to lowercase
{
    for(int i=0; i<instruction.length(); i++)
    {
        if (instruction[i]>='A' && instruction[i]<='Z')
        {
            instruction[i]+=32;
        }
    }
}

string toBinary(int num, int noOfBits) // Function for converting decimal to binary
{
    string s="";
    for(int i=0; i<noOfBits; i++)
    {
        if ((num & (1<<i))!=0)
        {
            s+="1";
        }
        else
        {
            s+="0";
        }
    }

    reverse(s.begin(),s.end());


    return s;

}

string toHex(string bin) // Hex converter
{
    stringstream ss;
    string val;
    ss << hex << stoll(bin, NULL, 2);
    val = ss.str();
    return val;
}

int main()
{
    cout << "Hello World" << endl;
    string instruction,op,rs,rt,rd,rz,r_type,str_constant,b,hex;
    int cont;
    map<string,string> R_type, I_type, RegValue; // maps for key-value pairing

    /*R_Type opcode and values*/
    R_type["add"] = "0000";
    R_type["sub"] = "0001";
    R_type["and"] = "0010";
    R_type["or"] = "0011";
    R_type["xor"] = "0100";
    R_type["nor"] = "0101";
    R_type["sll"] = "0110";
    R_type["srl"] = "0111";
    /*I_Type opcode and values*/
    I_type["addi"] = "1000";
    I_type["subi"] = "1001";
    I_type["andi"] = "1010";
    I_type["ori"] = "1011";
    I_type["xnori"] = "1100";
    I_type["nand"] = "1101";
    I_type["lw"] = "1110";
    I_type["sw"] = "1111";
    /*Register values*/
    RegValue["$r0"] = "00";
    RegValue["$r1"] = "01";
    RegValue["$r2"] = "10";
    RegValue["$r3"] = "11";

    ifstream inputFile("F:\\North South CSE\\Semester 5 - 17 Credits\\Cse332\\Project\\Risc\\Assembler ver 2\\input.txt");
    ofstream outputFile("F:\\North South CSE\\Semester 5 - 17 Credits\\Cse332\\Project\\Risc\\Assembler ver 2\\output.txt");

    while(getline(inputFile, instruction))
    {
        toLower(instruction);
        stringstream input;

        input << instruction;
        input >> op;
        if(R_type.find(op)!=R_type.end()) // R Type opcode
        {
            input >> rd;
            if(rd=="$00") // Exception handling
            {
                cout << "Invalid: $zero can not be used" << endl;
                outputFile << "Invalid: $zero can not be used" << endl;
            }

            else if(rd[0]!='$')
            {
                cout << "Invalid: '$' sign is missing before rd" << endl;
                outputFile << "Invalid: '$' sign is missing before rd" << endl;
                continue;
            }
            else if(rd[rd.size()-1]!=',')
            {
                cout << "Invalid: ',' is missing after rd" << endl;
                outputFile << "Invalid: ',' is missing after rd" << endl;
                continue;
            }
            rd.erase(rd.end()-1);
            input >> rs;

            if(rs[0]!='$')
            {
                cout << "Invalid: '$' sign is missing before rs" << endl;
                outputFile << "Invalid: '$' sign is missing before rs" << endl;
                continue;
            }
            else if(rs[rs.size()-1]!=',')
            {
                cout << "Invalid: ',' is missing after rs" << endl;
                outputFile << "Invalid: ',' is missing after rs" << endl;
                continue;
            }
            rs.erase(rs.end()-1);
            input >> rt;
            if(rt[0]!='$')
            {
                cout << "Invalid: '$' sign is missing before rt" << endl;
                outputFile << "Invalid: '$' sign is missing before rt" << endl;
                continue;
            }
            else if(rt[rt.size()-1]==',')
            {
                cout << "Invalid: invalid character after rt" << endl;
                outputFile << "Invalid: invalid character after rt" << endl;
                continue;
            } // Exception Handling

            if(R_type.find(op)!= R_type.end() && RegValue.find(rs)!= RegValue.end() && RegValue.find(rt)!= RegValue.end() && RegValue.find(rd)!= RegValue.end()) // Machine Code Write
            {

                if (op=="sll"||op=="srl")
                {
                    rs = "00";
                    hex = R_type[op] + rs + RegValue[rt] + RegValue[rd];
                    toHex(hex);
                    cout << R_type[op] << rs << RegValue[rt] << RegValue[rd] << endl;
                    outputFile << R_type[op] << rs << RegValue[rt] << RegValue[rd] << endl;
                    outputFile << toHex(hex) << endl;
                }

                else if (op=="add"||op=="sub" || op=="and"||op=="or" || op=="xor"||op=="nor")
                {
                    hex = R_type[op] + RegValue[rs] + RegValue[rt] + RegValue[rd];
                    toHex(hex);
                    //cout<< "hex = " << toHex(hex) << endl;
                    cout << R_type[op] << RegValue[rs] << RegValue[rt] << RegValue[rd] << endl;
                    outputFile << R_type[op] << RegValue[rs] << RegValue[rt] << RegValue[rd] << endl;
                    outputFile << toHex(hex) << endl;
                }
            }
        }

        else if(I_type.find(op)!=I_type.end()) // For i Type opcode
        {
            if(op=="lw"||op=="sw")
            {
                input >> rd >> str_constant >> rs;

                if(rd=="$00") // Exception handling
                {
                    cout << "Invalid: $zero can not be used" << endl;
                    outputFile << "Invalid: $zero can not be used" << endl;
                }

                else if(rd[0]!='$')
                {
                    cout << "Invalid: '$' sign is missing before rd" << endl;
                    outputFile << "Invalid: '$' sign is missing before rd" << endl;
                    continue;
                }
                else if(rd[rd.size()-1]!=',')
                {
                    cout << "Invalid: ',' is missing after rd" << endl;
                    outputFile << "Invalid: ',' is missing after rd" << endl;
                    continue;
                }
                if(rs[0]!='(') // Exception handling
                {
                    cout << "Invalid: '(' is missing before rs" << endl;
                    outputFile << "Invalid: '(' is missing before rs" << endl;
                    continue;
                }
                else if(rs[rs.size()-1]!=')')
                {
                    cout << "Invalid: ')' is missing after rs" << endl;
                    outputFile << "Invalid: ')' is missing after rs" << endl;
                    continue;
                }
                else if(rs[1]!='$')
                {
                    cout << "Invalid: '$' sign is missing before rs" << endl;
                    outputFile << "Invalid: '$' sign is missing before rs" << endl;
                    continue;
                }
                rd.erase(rd.end()-1);
                rs.erase(rs.begin());
                rs.erase(rs.end()-1);

            }

            else
            {
                input >> rd >> rs >> str_constant;
                if(rd=="$00") // Exception handling
                {
                    cout << "Invalid: $zero can not be used" << endl;
                    outputFile << "Invalid: $zero can not be used" << endl;
                }

                else if(rd[0]!='$')
                {
                    cout << "Invalid: '$' sign is missing before rd" << endl;
                    outputFile << "Invalid: '$' sign is missing before rd" << endl;
                    continue;
                }
                else if(rd[rd.size()-1]!=',')
                {
                    cout << "" << endl;
                    outputFile << "" << endl;
                    continue;
                }
                if(rs[0]!='$')
                {
                    cout << "Invalid: '$' sign is missing before rs" << endl;
                    outputFile << "Invalid: '$' sign is missing before rs" << endl;
                    continue;
                }
                else if(rs[rs.size()-1]!=',')
                {
                    cout << "Invalid: ',' is missing after rs" << endl;
                    outputFile << "Invalid: ',' is missing after rs" << endl;
                    continue;
                }
                rs.erase(rs.end()-1);
                rd.erase(rd.end()-1);

            } // Exception handling

            string::size_type sz;

            cont = stoi(str_constant,&sz); // string to int

            b = toBinary(cont,2);


            if(op!="addi"&&op!="subi"&&op!="andi"&&op!="ori"&&op!="xnori"&&op!="nandi"&&cont<0)
            {
                cout << "Invalid: value can not be neg" << endl;
                outputFile << "Invalid: value can not be neg" << endl;
            }
            else if(cont>4)
            {
                cout << "Invalid: value is too large" << endl;
                outputFile << "Invalid: value is too large" << endl;
            }
            else if(cont<0)
            {
                cout << "Invalid: value is too small" << endl;
                outputFile << "Invalid: value is too small" << endl;
            }
            else
            {

                hex = I_type[op] + RegValue[rs] + RegValue[rd] + b;
                toHex(hex);
                //cout<< "hex = " << toHex(hex) << endl;
                cout << I_type[op] << RegValue[rs] << RegValue[rd] << b << endl;
                outputFile << I_type[op] << RegValue[rs] << RegValue[rd] << b << endl;
                outputFile << toHex(hex) << endl;

            }

        }
        else
        {
            cout << "Invalid: Op code not found" << endl;
            outputFile << "Invalid: Op code not found" << endl;
        }
    }


    inputFile.close();
}

