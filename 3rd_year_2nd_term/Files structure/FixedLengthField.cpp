#include<iostream>
#include<fstream>
using namespace std;
struct  Student{
	char name [10];
	char id[15];
	int age;

};

void write(ofstream &out,Student s){
	out.write((char*)&s,sizeof(Student));
}

void read(ifstream &in){
	Student s;
	while(in.read((char*)&s,sizeof(Student))){
//		cout<<s.name<<" "<<s.id<<" "<< s.age<<endl;
		printf("name: %s,id: %s , age: %d\n",s.name,s.id,s.age);
	}
}

void readRec(ifstream &in, int rec){
	int offset = (rec - 1) *sizeof(Student);
	in.seekg(offset);
	Student target;
	in.read((char*)&target,sizeof(Student));
	cout<<"target student "<<target.name<<" "<<target.id<<" "<< target.age<<endl;
}

void update(fstream &out,int rec, Student newData){
	int offset = (rec - 1)*sizeof(Student);
	out.seekg(offset);
	Student temp;
	out.read((char*)&temp,sizeof(Student));
	cout<<"old data: "<<temp.name<<" "<<temp.age<<" "<< temp.id<<endl;
	out.clear();
	out.seekp(offset);
	out.write((char*)&newData,sizeof(Student));
}
int main(){
	ofstream out("bb.bin",ios::binary);
	Student s1;
//	s1.name = "tasneem"; X X X
	strcpy(s1.name,"Ahmed");
	strcpy(s1.id,"001");
	s1.age = 21;
//	Student s2 = {"mohamedfff","002",23};
//	strcpy(s2.name,"mohamedmohamedf");
	Student s2;
	strcpy(s2.name,"mohamed");
	strcpy(s2.id,"002");
	s2.age = 24;
	Student s3 = {"ali","003",22};
	
	
	write(out,s1);
	write(out,s2);
	write(out,s3);	
	out.close();

	ifstream in("bb.bin",ios::binary);
	read(in);
	cout<<"=================================="<<endl;
	in.close();
	
	fstream outt("bb.bin", ios::in | ios::out | ios::binary);
	Student newstd = {"tasneem","123",21};
//	strcpy(newstd.name,"gggg");
//	strcpy(newstd.id,"123gg");
//	newstd.age =22;
	update(outt,3,newstd);
	outt.close();
	cout<<"=================================="<<endl;
	in.open("bb.bin",ios::binary);
  	read(in);
  	in.close();
	cout<<"=================================="<<endl;	
	in.open("bb.bin",ios::binary);
	int rec = 3;
	readRec(in,rec);
	in.close();
}
//why to write binary not txt ?
	//Space Efficiency : in txt 1000 takes 4 places but bin any int takes 4 bytes
	//Performance & Speed : low ldevel
	//Security:  canot rea by human



// fixed length field =>  fixed length record
	//pros : direct access
	//cons : waste space 


//length indicator
//delimeter : require to read file character by character 
//Key Words
	//pros: clear
	// cons: space 
	

