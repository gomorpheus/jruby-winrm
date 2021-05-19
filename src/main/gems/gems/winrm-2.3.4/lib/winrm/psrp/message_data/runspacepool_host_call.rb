# Copyright 2016 Matt Wrock <matt@mattwrock.com>
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

module WinRM
  module PSRP
    module MessageData
      # runspace pool host call message type
      class RunspacepoolHostCall < Base
        def method_identifier
          clixml[:obj][0][:to_string]
        end

        def method_parameters
          clixml[:obj][1][:lst]
        end
      end
    end
  end
end
