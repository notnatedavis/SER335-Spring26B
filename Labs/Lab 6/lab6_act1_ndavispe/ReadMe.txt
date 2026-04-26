SER335 Lab5 – Code Changes

Task 1 – Exception Handling (AddUser functionality)

1. File : edu/asu/ser335/jfm/SaltsSingleton.java
   - Method: writeSaltsFile()
     - removed printStackTrace(), now throws IOException
   - Method: createSaltedPassword()
     - replaced generic catch with specific IOException
     - removed stack trace; rethrows user-friendly exception
     - added comment "SER335 LAB5"

2. File: edu/asu/ser335/jfm/UsersSingleton.java
   - Method: createPasswordMapping()
     - removed printStackTrace() from both catch blocks
     - rethrows exceptions with clear messages (no console noise)
   - Method: loadUsers()
     - replaced e.printStackTrace() with System.err.println (minimal)
   - added "SER335 LAB5" comments

3. File: org/jfm/po/AddUserPannel.java
   - Method: actionPerformed()
     - added defensive input validation (empty username/password)
     - replaced generic catch (Throwable) with specific exception handling
     - shows JOptionPane dialogs with detailed error messages
     - no stack traces printed to terminal
   - added "SER335 LAB5" comments

Task 2 – Defensive Programming (two instances)

1. File: org/jfm/po/DeleteAction.java
   - Method: deleteFile()
     - added null check for fi.listFiles() before iterating
     - prevents NullPointerException when directory is unreadable
   - added "SER335 LAB5: Defensive programming – added null check" comments

2. File: org/jfm/po/CopyAction.java
   - Method: copyDir()
     - added null check for dir.listFiles()
   - Method: copyFile()
     - added null checks for getInputStream() and getOutputStream()
     - shows error dialogs if streams are null
   - added "SER335 LAB5: Defensive – added null check" and stream checks" comments

All changes are also marked in the code with "// SER335 LAB5". No stack traces are printed
to stdout