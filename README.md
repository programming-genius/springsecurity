# Corso Spring Security
## Codice di esempio del corso.
### E' possibile utilizzare il proprio IDE preferito per la compilazione ed esecuzione del codice. Unico requisito sono le JDK 11.
### Seguire i successivi passi per avere una macchina virtuale Linux sul proprio PC.

## (1) Scaricare ed installare VMWare player:
### https://www.vmware.com/go/getplayer-win

## (2) Scaricare l'immagine Linux:
### Ubuntu 18.04.3 Bionic Beaver
### Username: osboxes
### Password: osboxes.org
### Attenzione! Selezionare tab VMWARE. Scegliere 64 bit o 32 bit a seconda dell'architettura del proprio PC.
### https://www.osboxes.org/ubuntu/#ubuntu-1804-info

## (3) Seguire la guida "How to attach/configure image with VMware (VMDK file version)? Per installare l'immagine del passo (2) 
### https://www.osboxes.org/guide/

## Nel caso di problemi di lock su risorsa eseguire
1. sudo killall apt apt-get
2. sudo rm /var/lib/apt/lists/lock
3. sudo rm /var/cache/apt/archives/lock
4. sudo rm /var/lib/dpkg/lock*
5. sudo dpkg --configure -a
6. sudo apt update


## Per funzionalità di intereazione host-macchina virtuale eseguire
sudo apt update
sudo apt install open-vm-tools-desktop
