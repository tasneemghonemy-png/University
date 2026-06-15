#include<iostream>
#include<fstream>
#include<sstream>
using namespace std;
//menna|30\0
struct Student{
	string name;
	int grade;
};
void write(ofstream &out,Student s){
	string rec = s.name + '|' + to_string(s.grade) + '\0';
//	out.write((char*)&rec[0],rec.length());
	out.write((char*)rec.c_str(),rec.length());
}

int update(fstream &in, string oldST, string newStName){
	string rec;
 	Student std;
	char c;
	int counter = 0;
	while(getline(in,rec,'\0')){
		stringstream ss(rec);
		if(getline(ss,std.name,'|')){
			if(std.name == oldST){
				in.seekp(-(rec.length()+1),ios::cur);
				in.clear();
//				in.write((char*)&newStName[0],newStName.length());
				in.write((char*)newStName.c_str(),newStName.length());
				return counter;
			}		
		}
//		cout<<counter<<endl;
		counter++;
	}
	return -1;
}
void read(ifstream &in){
	string rec;
	while(getline(in,rec,'\0')){
		cout<<rec<<endl;
	}
}
int main(){
	Student s1 = {"ahmed",20};
	Student s2 ={"ali",30};
	Student s3 ={"kha\0led",80};
	
	ofstream out("out1.bin",ios::binary|ios::trunc);
	write(out,s1);
	write(out,s2);
	write(out,s3);
	out.close();
	
	ifstream in("out1.bin");
	read(in);
	in.close();
	cout<<"================================="<<endl;
	fstream fs("out1.bin",ios::binary|ios::out|ios::in);
	cout<<update(fs,"ali","amr")<<endl;
	fs.close();
	cout<<"================================="<<endl;
	in.open("out1.bin");
	read(in);
	in.close();
}