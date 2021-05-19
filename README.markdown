# Jruby Winrm

This is a library that packages up the Ruby Winrm Client for handling encrypted NTLM Traffic and distributes it as a Java exposed class outside of a JRuby Runtime


##Usage

Groovy Sample
```groovy
import com.morpheusdata.jruby.JRubyWinrm;

JRubyWinrm winrm = new JRubyWinrm();

Boolean elevatedMode=false;
String user = 'username';
String password = 'password';
String command = 'echo hello';
String host = '10.0.0.1'
Integer port = 5985
Map results = winrm.runCommand(command,host,port,user,password,elevatedMode);

```


## Work To Be Done

* More documentation