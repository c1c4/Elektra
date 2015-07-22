#include <SPI.h>
#include <Ethernet.h>  // Biblioteca para comunicacao com Arduino
  
char *fromArduino;

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
IPAddress serverIP(192,168,0,105);
byte gateway[] = { 192, 168, 0, 1 };         // internet access via router
byte subnet[] = { 255, 255, 255, 0 };                   //subnet mask


int serverPort = 81;
int led_vermelho = 7;
int led_verde    = 8;
int led_amarelo  = 3;
int motor  = 13;

EthernetServer server(serverPort);
  

void setup(){
  
  pinMode(led_vermelho, OUTPUT);
  pinMode(led_verde, OUTPUT);
  pinMode(led_amarelo, OUTPUT);
  pinMode(motor, OUTPUT);
  Ethernet.begin(mac, serverIP,gateway, subnet);
  server.begin();  //  Inicia o servidor
}
  
void loop(){
  EthernetClient client = server.available();
  if (client) {
    while (client.connected()) {
      if (client.available()) {
        
        
        char c = client.read();
        
        
        if (c == '1') {
           digitalWrite(led_verde, HIGH);
           Serial.println("Led Vermelho Ligado");
           client.println("Led Vermelho Ligado"); //aqui envia do arduino para o android
          }

          
          if (!client.connected()) {
           client.stop();
          }
         }
   }
  delay(1);
  client.stop();
  }
}
