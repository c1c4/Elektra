#include <SPI.h>
#include <Ethernet.h>  // Biblioteca para comunicacao com Arduino
  
//Endereço físico (MAC ADDRESS) da placa de rede. 
byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
 
byte ip[] = {192, 168, 7, 105};  //Endereco IP.
     
EthernetServer server(80);
  
String readString;
//Pino digital onde será ligado e desligado o led no Arduino.
int Verde = 9; 
int Vermelho = 8; 
int Amarelo = 7;
void setup(){
  
  pinMode(Verde, OUTPUT);
  pinMode(Vermelho, OUTPUT);
  pinMode(Amarelo, OUTPUT);
  Ethernet.begin(mac, ip);
  server.begin();  //  Inicia o servidor
}
  
void loop(){
  EthernetClient client = server.available();
  if (client) {
    while (client.connected()) {
      if (client.available()) {
        char c = client.read();
  
        if (readString.length() < 100) {
          readString += c;             
        }
 
        if (c == '\n') {
           
          delay(1);
          client.stop();
           
        
          
          
          if(readString.indexOf("?ligar2") > 0){
            digitalWrite(Vermelho, HIGH);  //Liga
          }
            if(readString.indexOf("?desligar2") > 0){
              digitalWrite(Vermelho, LOW);  //Desliga
            }
          
          
           if(readString.indexOf("?ligar3") > 0){
            digitalWrite(Amarelo, HIGH);  //Liga
          }
            if(readString.indexOf("?desligar3") > 0){
              digitalWrite(Amarelo, LOW);  //Desliga
            }
            if(readString.indexOf("?ligar4") > 0){
           digitalWrite(Verde, HIGH);  //Liga
          } 
            if(readString.indexOf("?desligar4") > 0){
              digitalWrite(Verde, LOW);  //Desliga
            }
          readString="";    
        }
       
      }
    }
  }
}
