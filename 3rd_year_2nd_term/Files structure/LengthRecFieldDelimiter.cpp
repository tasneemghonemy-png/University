#include<iostream>
#include<fstream>
using namespace std;
//10omar\0hello
struct Chat{
	string user;
	string msg;
};
void write(ofstream &out,Chat c){
	string rec = c.user + '\0' + c.msg;	
	unsigned chatLen =  rec.length();
//	out.write((char*)&chatLen,sizeof(chatLen));
	out.write((char*)rec.c_str(),chatLen);
//		out.write((char*)&rec[0],chatLen);//with read
//	cout<<rec<<endl;
}
bool read(ifstream &in,Chat &c){
	unsigned int recLen;
	if(!in.read((char*)&recLen,sizeof(recLen))){
		cout<<"canot read"<<endl;
		return false;
	}
//	cout<<"recLen "<<recLen<<endl;
		string rec;
		rec.resize(recLen);
		in.read((char*)&rec[0],recLen);
		int x = rec.find('\0');
		c.user = rec.substr(0,x);
//		cout<<c.user<<endl;
		c.msg = rec.substr(x+1);
//		cout<<c.msg<<endl;
//		cout<<rec<<endl;
		return true;
	}
int main(){
	Chat c1 = {"tasneem","hello"};
	Chat c2 = {"aya","hi"};
	
	ofstream out("chat.bin",ios::binary|ios::app);
	if(out.is_open()){
		write(out,c1);
		write(out,c2);
		out.close();
	}else{
		cout<<"canot open to write";
	}
	
	ifstream in("chat.bin",ios::binary);
	if(in.is_open()){
		Chat c;
		while(read(in,c)){
			cout<< c.user<<": "<<c.msg<<endl;
		}
		in.close();	
	}else{
		cout<<"canot open to read";
	}
}