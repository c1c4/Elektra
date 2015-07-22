// Programa : Sensor de presenca com modulo PIR
// Autor : Arduino e Cia
#define BUZZER 3
int pinoledverm = 9; //Pino ligado ao led vermelho
int pinoledazul = 8; //Pino ligado ao led azul
int pinopir = 6;  //Pino ligado ao sensor PIR
int acionamento;  //Variavel para guardar valor do sensor

void setup()
{
  pinMode(pinoledverm, OUTPUT); //Define pino como saida
  pinMode(pinoledazul, OUTPUT); //Define pino como saida
  pinMode(pinopir, INPUT);   //Define pino sensor como entrada
  pinMode(BUZZER, OUTPUT);
  digitalWrite(BUZZER, LOW);

}

void loop()
{
  
 acionamento = digitalRead(pinopir); //Le o valor do sensor PIR
 if (acionamento == LOW)  //Sem movimento, mantem led azul ligado
 {
    digitalWrite(pinoledverm, LOW);
    digitalWrite(pinoledazul, HIGH);
    digitalWrite(BUZZER, HIGH);
  
    
 }
 else  //Caso seja detectado um movimento, aciona o led vermelho
 {
    digitalWrite(pinoledverm, HIGH);
    digitalWrite(pinoledazul, LOW);
    digitalWrite(BUZZER, LOW);
    delay(1000);
    
 }
}
