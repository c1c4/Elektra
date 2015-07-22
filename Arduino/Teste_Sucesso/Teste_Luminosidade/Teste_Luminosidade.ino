int portaLed = 48; //Porta a ser utilizada para ligar o led  
int portaLDR = A5; //Porta analógica utilizada pelo LDR  
  
void setup()  
{  
  pinMode(portaLed, OUTPUT); //Define a porta do Led como saída  
  Serial.begin(9600);
}  
   
void loop()  
{  
  int estado = analogRead(portaLDR);  //Lê o valor fornecido pelo LDR  
   Serial.println(estado);
   delay(1000);
  // Caso o valor lido na porta analógica seja maior do que 
  // 800, acende o LED  
  // Ajuste o valor abaixo de acordo com o seu circuito  
  if (estado > 1000)    
  {  
    digitalWrite(portaLed, LOW);
      
  }  
  else  //Caso contrário, apaga o led  
  {  
      digitalWrite(portaLed, HIGH);
  }  
}  
