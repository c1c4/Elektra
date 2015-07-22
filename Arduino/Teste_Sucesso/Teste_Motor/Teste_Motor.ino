int MotorPin =  7;
 
void setup()
{
  pinMode(MotorPin, OUTPUT);
}
 
void loop()
{
  digitalWrite(MotorPin, HIGH);
  delay(1000);
  digitalWrite(MotorPin, LOW);
  delay(10000);
  
}
