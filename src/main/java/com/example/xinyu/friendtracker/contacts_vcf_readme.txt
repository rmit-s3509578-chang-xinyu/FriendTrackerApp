You can copy this dummy contacts.vcf (it only contains 4 contacts to get you started) to the AVD file system using the Android Device Monitor 
(Tools->Android->Android Device Monitor in Android Studio).
Then select the file explorer tab
The Download folder for the internal storage path is storage->emulated->0->Download (you can drag contacts.vcf here)

You can then import the contacts.vcf via the contacts app in the AVD.

IN API 23 emulator you can follow these steps (different versions may be a bit different)
Menu->import/export
Import from .vcf file
On the next screen make sure menu menu option Show SD card is selected
You can then import the contacts.vcf you previously copied to the AVD and four dummy contacts should display

You can also export your own contacts.vcf from www.gmail.com.
