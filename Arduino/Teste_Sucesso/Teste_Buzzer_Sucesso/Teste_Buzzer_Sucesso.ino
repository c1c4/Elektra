
#define BUZZER 3

void setup() {
  // put your setup code here, to run once:
pinMode(BUZZER, OUTPUT);
 digitalWrite(BUZZER, LOW);
}

void loop() {
  // put your main code here, to run repeatedly:
digitalWrite(BUZZER, HIGH);
delay(2000);


digitalWrite(BUZZER, LOW);
delay(2000);
}
