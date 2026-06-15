#include<iostream>
#include<fstream>
#include<Vector>
#include<string>
using namespace std;
void createFile(vector<string>records){
	ofstream index("index.bin",ios::binary);
	ofstream rec("data.bin",ios::binary);
	
	unsigned int offset = 0;
	
	for(int i = 0; i < records.size(); i++){
		
		index.write((char*)&offset,sizeof(unsigned int));
		rec.write(records[i].c_str(),records[i].size());
		
		offset += records[i].size();
	}
	index.write((char*)&offset,sizeof(unsigned int));
	
	index.close();
	rec.close();
}

void printRec(int recordNumber){
	ifstream index("index.bin",ios::binary);
	ifstream rec("data.bin",ios::binary);
	
	unsigned int start, end, diff;
	
	index.seekg((recordNumber - 1)*sizeof(unsigned int));
	index.read((char*)&start,sizeof(unsigned int));
	index.read((char*)&end,sizeof(unsigned int));
	
	diff = end - start;

	string msg;
	msg.resize(diff);
	
	rec.seekg(start);
	rec.read(&msg[0],diff);
	
	cout<<"msg: " << msg << endl;
	
	index.close();
	rec.close();
	
}
void printFirstRec(){
	ifstream index("index.bin",ios::binary);
	ifstream rec("data.bin",ios::binary);
	
	unsigned int start, end, diff;
	
	index.read((char*)&start,sizeof(unsigned int));
	index.read((char*)&end,sizeof(unsigned int));
	
	diff = end - start;

	string msg;
	msg.resize(diff);
	
	rec.read(&msg[0],diff);
	
	cout<<"msg: " << msg << endl;
	
	index.close();
	rec.close();
	
}
void printFirstRec2(){
	printRec(1);
}

int main(){
	vector<string> records;
	records.push_back("karim");
	records.push_back("Alaa");
	records.push_back("Ali");
	records.push_back("Asmaa");
	records.push_back("Assim");
	records.push_back("ahmed");

	createFile(records);
//	printFirstRec();
//	printRec(3);
//	printRec(6);
//	printRec(1);

	printFirstRec2();
	
}