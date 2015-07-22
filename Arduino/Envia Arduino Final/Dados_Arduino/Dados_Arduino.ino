// Bibliotecas
#include <SPI.h> // >> Esta biblioteca permite que você se comunique com dispositivos de SPI, com o Arduino e o dispositivo mestre <<
#include <Ethernet.h> // >> Esta biblioteca permite que uma placa Arduino para se conectar à internet. Ela pode servir como um servidor de aceitar conexões de entrada ou um cliente fazendo aqueles enviados.<<
#include <dht.h> // >> Esta biblioteca permite se conectar com o sensor de temperatura <<

// FIM BIBLIOTECAS

// CONFIGURAÇÃO DA ETHERNET SHIELD

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
IPAddress serverIP(192, 168, 1, 105);
byte gateway[] = {192, 168, 1, 1};
byte subnet[] = {255, 255, 255, 0};
int serverPort = 81;

// FIM DA CONFIGURAÇÃO ETHERNET SHIELD


// PINO ANALOGICO E DIGITAL DO SENSOR DE CHUVA

int pino_d = 26;
int pino_a = A0;
int val_d = 0;
int val_a = 0;
int val_chuva = 0;

// FIMP INO ANALOGICO E DIGITAL DO SENSOR DE CHUVA

// PINO BUZZER E MOVIMENTO

#define BUZZER 3
int pinopir = 8;
int acionamento;
int variavel_movimento = 0;

// FIM BUZZER E MOVIMENTO


// LUMINOSIDADE

int  variavel_luminosidade = 0;
int portaLDR = A5;

// FIM LUMINOSIDADE


//SENSOR DE TEMPERATURA

#define dht_dpin A2
dht DHT;
int val_temp = 0;


// FIM SENSOR TEMPERATURA

// LEDS_PORTAS

int led_banheiro  = 31;
int led_cozinha   = 39;
int led_area      = 48;
int led_sala      = 45;
int led_quarto    = 53;


// FIM LEDS_PORTAS

// VENTILADOR_PORTA

int ventilador = 7;

// FIM VENTILADOR_PORTA


// MOTOR

int led_Motor    = 43;

// FIM MOTOR



EthernetServer server(serverPort);


void setup() {

  // LEDS
  pinMode(led_quarto, OUTPUT);
  pinMode(led_sala, OUTPUT);
  pinMode(led_cozinha, OUTPUT);
  pinMode(led_banheiro, OUTPUT);
  pinMode(led_area, OUTPUT);

  // VENTILADOR
  pinMode(ventilador, OUTPUT);

  //CHUVA
  pinMode(pino_d, INPUT);
  pinMode(pino_a, INPUT);

  // MOVIMENTO
  pinMode(pinopir, INPUT);

  // BUZZER = BUZINA
  pinMode(BUZZER, OUTPUT);
  digitalWrite(BUZZER, HIGH); // FAZ COM QUE ELA NÃO COMECE A APITAR ASSIM QUE LIGAR O ARDUINO

  // MOTOR
  pinMode(led_Motor, OUTPUT);

  // PASSA OS PARAMETROS PARA A SHIELD
  Ethernet.begin(mac, serverIP, gateway, subnet);
  // STARTA A SHIELD
  server.begin();

  // STARTA A SERIAL - OBS
  Serial.begin(9600);

}

void loop() {
  
   // Verifica Variavel da Luminosidade
  // Serial.println(variavel_luminosidade);
  if (variavel_luminosidade == 1) {
    int estado = analogRead(portaLDR);
    
    
    if (estado < 8)
    {
      digitalWrite(led_area, HIGH);
     // Serial.println("ta ligado");
     
    }
    else
    {
      
      digitalWrite(led_area, LOW);
      
      //Serial.println("ta Desligado");
     
    }
  }
  else
  {
    
  // Serial.println("não faz nada");

    // não faz nada

  }

  
  if(val_temp == 1){
    DHT.read11(dht_dpin);
    Serial.println(DHT.temperature);
    if (DHT.temperature > 10){
      
      digitalWrite(ventilador, HIGH);
      
    }
    else
    {
      digitalWrite(ventilador, LOW);
    }
  }

  
  

  if (val_chuva == 1)
  {
    val_a = analogRead(pino_a);
    if (val_a > 900)
    {
      digitalWrite(led_Motor, LOW);
      delay(2000);
    }
    if (val_a > 500 && val_a < 900)
    {

      digitalWrite(led_Motor, HIGH);
      delay(2000);
    }
    if (val_a < 499)
    {
      digitalWrite(led_Motor, HIGH);
      delay(2000);
    }


  }
  else
  {
    digitalWrite(led_Motor, LOW);
  }

  // Verifica Variavel de Movimento
  if (variavel_movimento == 1) {

    acionamento = digitalRead(pinopir);
    if (acionamento == LOW)
    {
      digitalWrite(BUZZER, HIGH);
    }
    else
    {
      digitalWrite(BUZZER, LOW);
      delay(1000);
    }
  }
  else
  {
    // não faz nada
  }


  EthernetClient client = server.available();
  if (client) {
    while (client.connected()) {
      if (client.available()) {

        int c = client.read();

        // AREA

        if (c == 1) {
          digitalWrite(led_area, HIGH);
          client.println("ok");
        }

        if (c == 2) {
          digitalWrite(led_area, LOW);
          client.println("desligar");
        }

        // BANHEIRO

        if (c == 3) {
          digitalWrite(led_banheiro, HIGH);
           client.println("ok");
        }

        if (c == 4) {
          digitalWrite(led_banheiro, LOW);
           client.println("desligar");
        }

        // Cozinha

        if (c == 5) {
          digitalWrite(led_cozinha, HIGH);
           client.println("ok");
        }

        if (c == 6) {
          digitalWrite(led_cozinha, LOW);
           client.println("desligar");
        }

        // Quarto

        if (c == 7) {
          digitalWrite(led_quarto, HIGH);
           client.println("ok");
        }

        if (c == 8) {
          digitalWrite(led_quarto, LOW);
           client.println("desligar");
        }

        // SALA

        if (c == 9) {
          digitalWrite(led_sala, HIGH);
           client.println("ok");
        }

        if (c == 10) {
          digitalWrite(led_sala, LOW);
           client.println("desligar");
        }

        // LUMINOSIDADE

        if (c == 11) {
          variavel_luminosidade = 1;
           client.println("ok");
        }

        if (c == 12) {
          variavel_luminosidade = 0;
          digitalWrite(led_area, LOW);
           client.println("desligar");
        }


        // MOVIMENTO
        if (c == 13) {
          variavel_movimento = 1;
           client.println("ok");
        }

        if (c == 14) {
          variavel_movimento = 0;
          digitalWrite(BUZZER, HIGH);
           client.println("desligar");
        }

        if (c == 16) {
          digitalWrite(ventilador, HIGH);
           client.println("ok");
        }

        if (c == 17) {
          digitalWrite(ventilador, LOW);
           client.println("desligar");
        }

        if (c == 18) {
          val_chuva = 1;
           client.println("ok");
        }
        if (c == 19) {
          val_chuva = 0;
           client.println("desligar");
        }

        // falta o 18 verificar temperatura
        if (c == 20) {
          val_temp = 1;
           client.println("ok");
            
        }
        
        if (c == 21) {
          val_temp = 0;
           client.println("desligar");
           digitalWrite(ventilador, LOW);
           
        }
      }

      if (!client.connected()) {
        client.stop();
      }
    }
  }
  delay(1);
  client.stop();
}

