
# Allt til ad safna data um aefingu hja user.

## Database

Scrap this:

1. Basically byrja a thvi ad bua til session i datbase

Thad var nog ad setja bara boolean a setlist aukalega

2. SetList(Session) heldur utan um state aefingar i session object a database
3. I hvert sinn sem er checked off --> senda yfir a database 'true/false' fyrir aefingu i
   session (basically update-a setList og senda sidan aftur til baka)
4. I hvert sinn sem 'session' (fila gaesalappir sem eg gerdi adur, relevant) er byrjad tha bua til nytt entry i
   'history' hja 'user' sem segir til hvenaer hann byrjadi

## Vue

1. Routine(var Session) >:'D verdur med link inn a 'new routine'
2. Bua til link inn a session sidu. (Real stuff)
3. Aefingar eru checked off eftir thvi sem thaer eru klaradar
4. I hvert sinn sem er checked off --> senda yfir a database
5. I hvert sinn sem 'session' er byrjad tha bua til nytt entry i
   'history' hja 'user' sem segir til hvenaer hann byrjadi
