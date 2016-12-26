/**
 * Copyright [2016] [Muhammad Sadiq]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.uclab.scl.framework.recbuilder;

/**
 * UnsupportedTypeException
 * <p>
 * This class handles all sort of exceptions that occurs due to unsupported type (data types not supported by this 
 * application) of parameters or operators
 * 
 * @author Sadiq
 * @version 2.5
 * @since july 2014
 *
 */
public class UnsupportedTypeException extends Exception {
  
  private static final long serialVersionUID = -8522054585057940498L;
  
  public UnsupportedTypeException() { 
    super(); 
  }
  public UnsupportedTypeException(String message) { 
    super(message); 
  }
  public UnsupportedTypeException(String message, Throwable cause) { 
    super(message, cause); 
  }
  public UnsupportedTypeException(Throwable cause) { 
    super(cause); 
  }
  
}
