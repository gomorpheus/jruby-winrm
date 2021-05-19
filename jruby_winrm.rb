require 'winrm'
require 'winrm-elevated'
class JRubyWinrm  
  def runCommand(winrm_command,winrm_address,winrm_port,user,password,elevated=false)
  	endpoint = "http://#{winrm_address}:#{winrm_port}/wsman"
  	if winrm_port == 5986
  		endpoint = "https://#{winrm_address}:#{winrm_port}/wsman"
  	end
  	
	winrm_stdout = ""
	winrm_stderr = ""
	winrm_exit_code = "0"
	winrm_output = Hash.new
	opts = { 
	  :endpoint => endpoint,
	  :user => user,
	  :password => password
	}
	if winrm_port == 5986
		opts[:transport] = :ssl
		opts[:no_ssl_peer_verification] = true
	end
	conn = WinRM::Connection.new(opts)
	if elevated == true
		conn.shell(:elevated) do |shell|
		  winrm_output = shell.run(winrm_command) do |stdout, stderr|
		    unless stdout.nil?
				winrm_stdout << stdout
			end
			unless stderr.nil?
				winrm_stderr << stderr
			end
		  end
		  winrm_exit_code = winrm_output.exitcode.to_s
		end
	else
		conn.shell(:powershell) do |shell|
		  winrm_output = shell.run(winrm_command) do |stdout, stderr|
		    unless stdout.nil?
				winrm_stdout << stdout
			end
			unless stderr.nil?
				winrm_stderr << stderr
			end
		  end
		  winrm_exit_code = winrm_output.exitcode.to_s
		end
	end
    return {'exitCode' => winrm_exit_code, 'errorOutput' => winrm_stderr, 'data' => winrm_stdout}
  end
end
