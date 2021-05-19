package com.morpheusdata.jruby;

import org.jruby.Ruby;
import org.jruby.RubyObject;
import org.jruby.runtime.Helpers;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaUtil;
import org.jruby.RubyClass;


public class JRubyWinrm extends RubyObject  {
    private static final Ruby __ruby__ = Ruby.getGlobalRuntime();
    private static final RubyClass __metaclass__;

    static {
        String source = new StringBuilder("require 'winrm'\n" +
            "require 'winrm-elevated'\n" +
            "class JRubyWinrm  \n" +
            "  def runCommand(winrm_command,winrm_address,winrm_port,user,password,elevated=false)\n" +
            "  	endpoint = \"http://#{winrm_address}:#{winrm_port}/wsman\"\n" +
            "   if winrm_port == 5986\n" +
            "       endpoint = \"https://#{winrm_address}:#{winrm_port}/wsman\"\n" +
            "   end\n" +
            "	winrm_stdout = \"\"\n" +
            "	winrm_stderr = \"\"\n" +
            "	winrm_exit_code = \"0\"\n" +
            "	winrm_output = Hash.new\n" +
            "	opts = { \n" +
            "	  :endpoint => endpoint,\n" +
            "	  :user => user,\n" +
            "	  :password => password\n" +
            "	}\n" +
            "	if winrm_port == 5986\n" +
            "		opts[:transport] = :ssl\n" +
            "       opts[:no_ssl_peer_verification] = true\n" +
            "	end\n" +
            "	conn = WinRM::Connection.new(opts)\n" +
            "	if elevated == true\n" +
            "		conn.shell(:elevated) do |shell|\n" +
            "		  winrm_output = shell.run(winrm_command) do |stdout, stderr|\n" +
            "		    unless stdout.nil?\n" +
            "				winrm_stdout << stdout\n" +
            "			end\n" +
            "			unless stderr.nil?\n" +
            "				winrm_stderr << stderr\n" +
            "			end\n" +
            "		  end\n" +
            "		  winrm_exit_code = winrm_output.exitcode.to_s\n" +
            "		end\n" +
            "	else\n" +
            "		conn.shell(:powershell) do |shell|\n" +
            "		  winrm_output = shell.run(winrm_command) do |stdout, stderr|\n" +
            "		    unless stdout.nil?\n" +
            "				winrm_stdout << stdout\n" +
            "			end\n" +
            "			unless stderr.nil?\n" +
            "				winrm_stderr << stderr\n" +
            "			end\n" +
            "		  end\n" +
            "		  winrm_exit_code = winrm_output.exitcode.to_s\n" +
            "		end\n" +
            "	end\n" +
            "    return {'exitCode' => winrm_exit_code, 'errorOutput' => winrm_stderr, 'data' => winrm_stdout}\n" +
            "  end\n" +
            "end\n" +
            "").toString();
        __ruby__.executeScript(source, "jruby_winrm.rb");
        RubyClass metaclass = __ruby__.getClass("JRubyWinrm");
        if (metaclass == null) throw new NoClassDefFoundError("Could not load Ruby class: JRubyWinrm");
        metaclass.setRubyStaticAllocator(JRubyWinrm.class);
        __metaclass__ = metaclass;
    }

    /**
     * Standard Ruby object constructor, for construction-from-Ruby purposes.
     * Generally not for user consumption.
     *
     * @param ruby The JRuby instance this object will belong to
     * @param metaclass The RubyClass representing the Ruby class of this object
     */
    private JRubyWinrm(Ruby ruby, RubyClass metaclass) {
        super(ruby, metaclass);
    }

    /**
     * A static method used by JRuby for allocating instances of this object
     * from Ruby. Generally not for user comsumption.
     *
     * @param ruby The JRuby instance this object will belong to
     * @param metaClass The RubyClass representing the Ruby class of this object
     */
    public static IRubyObject __allocate__(Ruby ruby, RubyClass metaClass) {
        return new JRubyWinrm(ruby, metaClass);
    }

    /**
     * Default constructor. Invokes this(Ruby, RubyClass) with the classloader-static
     * Ruby and RubyClass instances assocated with this class, and then invokes the
     * no-argument 'initialize' method in Ruby.
     */
    public JRubyWinrm() {
        this(__ruby__, __metaclass__);
        Helpers.invoke(__ruby__.getCurrentContext(), this, "initialize");
    }


    
    public Object runCommand(Object winrm_command, Object winrm_address, Object winrm_port, Object user, Object password, Object elevated) {
        IRubyObject ruby_args[] = new IRubyObject[6];
        ruby_args[0] = JavaUtil.convertJavaToRuby(__ruby__, winrm_command);
        ruby_args[1] = JavaUtil.convertJavaToRuby(__ruby__, winrm_address);
        ruby_args[2] = JavaUtil.convertJavaToRuby(__ruby__, winrm_port);
        ruby_args[3] = JavaUtil.convertJavaToRuby(__ruby__, user);
        ruby_args[4] = JavaUtil.convertJavaToRuby(__ruby__, password);
        ruby_args[5] = JavaUtil.convertJavaToRuby(__ruby__, elevated);

        IRubyObject ruby_result = Helpers.invoke(__ruby__.getCurrentContext(), this, "runCommand", ruby_args);
        return (Object)ruby_result.toJava(Object.class);

    }

}
