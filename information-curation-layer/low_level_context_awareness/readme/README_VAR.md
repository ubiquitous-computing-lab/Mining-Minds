Video-based Activity Recognition

<!-- make your own badges from here: http://shields.io/ -->
[![Version](https://img.shields.io/badge/mining%20minds-version%202.5-green.svg)](http://www.miningminds.re.kr/english/)
[![License](https://img.shields.io/badge/Apache%20License%20-Version%202.0-yellowgreen.svg)


--------------------------



- [1. Introduction](#1-introduction)
   
- [2. Getting started](#2-getting-Started)
    - [2.1 Requirements](#21-requirements)
    - [2.2 Installation](#22-installation)
    - [2.3 Usage](#23-usage)
	
- [3. Features](#3-features)
   
- [4. Contributing](#4-contributing)
   
- [5. Author](#5-author)

- [6. License](#6-license)




# 1. Introduction


VAR module is built to recognize the indoor activities using the Microsoft Kinect sensor v2.0. 
The input is the skeleton data which is represented as the body joint coordinates in the 3-dimension. 
The output which is return by module is the activity label. 
In this version, VAR is able to recognize up to six indoor activities: standing, lying, stretching, sweeping, eating, and sitting. 
Some materials need to be prepared for this tutorial: Matlab 2016a, Weka 3.6.2, Eclipse IDE for Java EE Developers, and Apache Tomcat.


# 2. Getting Started


## 2.1 Requirements


- Kinect for Windows SDK 2.0 with OS Windows 8, 8.1, and 10. Visual Studio 2012 or Visual Studio 2013 is also required in advance. [https://www.microsoft.com/en-us/download/details.aspx?id=44561]
- MATLAB and Simulink R2016a [https://mathworks.com/products/matlab.html]
- Weka 3.8 [http://www.cs.waikato.ac.nz/ml/weka/downloading.html]
- Eclipse IDE for Java EE Developers [http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/keplersr2]
- Apache Tomcat 9 - requires JDK 1.7. It will NOT work with JDK 1.6. If your JDK is below 1.7, upgrade it (See JDK How-To). You can check your JDK version via command "javac -version". [http://tomcat.apache.org/download-90.cgi]
- Postman App [https://www.getpostman.com/app/download/win64]

Recommended Hardware Configuration [https://www.microsoft.com/en-us/download/details.aspx?id=44561]

- 64-bit (x64) processor
- 4 GB Memory (or more)
- Physical dual-core 3.1 GHz (2 logical cores per physical) or faster processor
- USB 3.0 controller dedicated to the Kinect for Windows v2 sensor*
- DX11 capable graphics adapter**
- A Microsoft Kinect v2 sensor, which includes a power hub and USB cabling

* If you're adding USB 3.0 functionality to your exisiting PC through a USB 3.0 host controller adapter, please ensure that it is a Windows 8 compliant device and that it supports Gen-2. See the release notes for additional information regarding USB 3.0 compatibility.

## 2.2 Installation


## 2.2.1 Kinect for Windows SDK 2.0


To install the Kinect for Windows SDK 2.0:
- Make sure the Kinect sensor is not plugged into any of the USB ports on the computer.
- From the download location, double-click on KinectSDK-v2.0_1409-Setup.exe
- Once the Kinect for Windows SDK has completed installing successfully, ensure the Kinect sensor is connected to the power hub and the power hub is plugged into an outlet. Plug the USB cable from the power hub into a USB 3.0 port on your computer. Driver installation will begin automatically.
- Wait for driver installation to complete. You can verify that installation has completed by launching Device Manager and verifying that "KinectSensor Device" exists in the device list. Note:On first plugin, the firmware on the device will be updated. This may result in the device enumeration happening several times in the first minute.
- Installation is now complete.


## 2.2.2 MATLAB and Simulink R2016a


The installation guide is available here [https://www.mathworks.com/help/install/ug/install-mathworks-software.html]


## 2.2.3 Weka 3.8


To install Java and Weka, please do the following:
- Install the JRE (Java Runtime Environment). You can download it from java.com. (This requires reboot in windows.)
- Install WEKA. You can download it from the Weka home page. This home page also has a single download that includes JRE too.
To run WEKA, you should open a command prompt window (on Windows) or a terminal window (on mac or unix) and go to the "weka-3-4" folder. Then issue the command
     java -jar weka.jar
Sometimes, WEKA may run out of memory. If this happens, you can rerun it and tell java to allocate more memory. I use the following command:
     java -Xmx200m -jar weka.jar

	 
## 2.2.4 Eclipse IDE for Java EE Developers


- Go to http://eclipse.org and download the "Eclipse IDE for Java EE Developers"
- IMPORTANT: If you already have Eclipse installed, you may have a problem where both the old and new Eclipse installations are in a folder called "eclipse". The solution is to create a folder called "eclipse_jee" at the desired install location, e.g. Program Files, and copy the contents of the eclipse folder in the download to the eclipse_jee folder.
- Likewise any shortcut you create to the Eclipse Java EE program will have the same name as the existing Eclipse shortcut. Rename the new shortcut to "Eclipse EE" to distinguish it from the other Eclipse install.
- Install Subclipse and UML Lab as normal.
- Note that WindowBuilder does not come with the Eclipse Java EE version.  This is because web-based GUIs are not Swing or SWT-based interfaces, so WindowBuilder is not usually needed. If you do need it for some reason, see the manual WindowBuilder install instructions in the above Eclipse Resources site.


## 2.2.5 Support Package Installer For Matlab-Kinect Connection


The installation guide is available here [https://www.mathworks.com/help/matlab/matlab_external/support-package-installer-help.html?s_tid=gn_loc_drop]


## 2.2.6 Apache Tomcat 9


Installing Tomcat on Windows can be done easily using the Windows installer. Its interface and functionality is similar to other wizard based installers, with only a few items of interest.

- Installation as a service: Tomcat will be installed as a Windows service no matter what setting is selected. Using the checkbox on the component page sets the service as "auto" startup, so that Tomcat is automatically started when Windows starts. For optimal security, the service should be run as a separate user, with reduced permissions (see the Windows Services administration tool and its documentation).
- Java location: The installer will provide a default JRE to use to run the service. The installer uses the registry to determine the base path of a Java 7 or later JRE, including the JRE installed as part of the full JDK. When running on a 64-bit operating system, the installer will first look for a 64-bit JRE and only look for a 32-bit JRE if a 64-bit JRE is not found. It is not mandatory to use the default JRE detected by the installer. Any installed Java 7 or later JRE (32-bit or 64-bit) may be used.
- Tray icon: When Tomcat is run as a service, there will not be any tray icon present when Tomcat is running. Note that when choosing to run Tomcat at the end of installation, the tray icon will be used even if Tomcat was installed as a service.
Refer to the Windows Service HOW-TO for information on how to manage Tomcat as a Windows service.
The installer will create shortcuts allowing starting and configuring Tomcat. It is important to note that the Tomcat administration web application can only be used when Tomcat is running.


## 2.2.7 Postman


Postman is a Chrome App. This means that Postman can only run on the Chrome browser. To use Postman, you will first need to install Google Chrome [https://www.google.com/chrome/]
Add Postman to Dock:
- Open Postman through the launcher
- Right click the Postman icon and select “Pin icon in the task bar”.


## 2.3 Usage


## 2.3.1 Kinect Data Collection


Refer and utilize available function of Acquiring Image and Skeleton Data Using Kinect in Image Acquisition Toolbox for data source selection and parameter configuration. Some useful function for data collection [2]:
- Add Unility Function to the Matlab path
	utilpath = fullfile(matlabroot, 'toolbox', 'imaq', 'imaqdemos','html','KinectForWindows');
	addpath(utilpath);
- See What Kinect for Windows Devices and Formats are Available
	hwInfo = imaqhwinfo('kinect')
	hwInfo.DeviceInfo(1)
	hwInfo.DeviceInfo(2)
- Configure Skeletal Tracking
	depthSrc = getselectedsource(depthVid)
	depthSrc.TrackingMode = 'Skeleton';
- Access Skeletal Data
	colorVid.FramesPerTrigger = 100;
	depthVid.FramesPerTrigger = 100;
	start([colorVid depthVid]);
	trigger([colorVid depthVid]);
	[frameDataColor] = getdata(colorVid);
	[frameDataDepth, timeDataDepth, metaDataDepth] = getdata(depthVid);
	jointCoordinates = metaDataDepth(95).JointWorldCoordinates(:, :, trackedSkeletons)
- Write the skeletal data as the text file with the pre-identified format type.
	csvwrite(SkeletonDataFromKinect.txt',jointCoordinates);

Format the skeleton data following format:
		[[x1,y1,z1,...,x25,y25,z25],
		 [x1,y1,z1,...,x25,y25,z25],
		 ...
		 [x1,y1,z1,...,x25,y25,z25]]
 
Each row is skeleton of a body detected in a screen. Totally there are 75 values of 25 3D joint coordinates. 
As a default setting of frame rate, 30 frames corresponding to 30 body skeletons are captured during each second. 
However, to reduce the computational cost for real-time processing, the skeleton data is recorded with 10fps, so for each activity window size of 3-second, there are 30 rows of data are generated. 

	
## 2.3.2 Classification Model Generation 


The training data (in this case, this is the extracted features from skeleton data which is processed in Matlab) has to be prepared. 
The classification model is created from the training data for predict the activity label from the new testing data. 
- Open Weka and select Explorer
- On the Weka Explorer, select Open file… in Preprocess menu to choose the training data file *.csv
- Move to Classify menu, click Choose to select the classification technique. For example, weka → classifiers → functions → SMO.
- Set up the training process parameters, such as, cross-validation. More options… for detail setting. 
- Click Star for training process. In the Result list window, right click to save model.

## 2.3.3 VAR Module Java Code


In this tutorial, three main components in VAR module, including spatial feature calculation KinectARFeatureExtraction.java, temporal feature extraction KinectARFeatureExtraction4.java, and classification KinectARClassification.java are discussed and explained in detail to help users easier in use of code. 
Based on appropriate applications, the module can be modified on the general structure. 

Spatial Feature Calculation (KinectARFeatureExtraction.java)
- Due to the optimal spatial features are pre-identified using the feature ranking technique  in Matlab, only 70 distance features and 70 angle features are calculated based on the joint index data. 
- The number of features and the joint index are available to update for the new module by change the data of siD, sjD, siA, sjA and the array length.

Temporal Feature Extraction (KinectARFeatureExtraction4.java)
- The array size for containing the spatial features during a window of 3-seconds is defined.
- The temporal features are calculated as the mean and standard deviation of spatial features. The other descriptive statistical metrics can be used by modifying or updating.  

Classification (KinectARClassification.java)
- The current classification model is created to recognize six activities, however, it can be updated for the new classes. 
- The classification model is able to update the new one (using Weka 3.6 to generate) by overwrite the current model at the same folder.


## 2.3.4 VAR Module API


The API function for VAR module is designed to use through the web service in which the input is only the skeleton data and the output is the activity label. 
For preparation, the Apache Tomcat has to be installed with JDK requirement (if not installed yet). 

- After Tomcat installation, copy file API VARAPI.war to inside the Tomcat folder (…\apache-tomcat-9.0.0.M11-windows-x86\VARAPI.war)
- Move to apache-tomcat-9.0.0.M11-windows-x86\apache-tomcat-9.0.0.M11\bin\
- Run startup.bat and tomcat9.exe
- For running API function, install Postman builder, an app for build, test, and document the APIs. 
- In the Postman, select the Builder menu, create the new tab. Select the POST in the Pop-up menu and input the URL address of web service for API running: localhost:8080/VARAPI/rest/VARService/classifyWithDefaultModel
- Select the Body tab, choose the x-www-forum-urlencoded radio buttion.
- Create the new key, named as input
- Copy the skeleton data for testing
- Click Send button


# 3. Features


- Recognize up to six indoor activities with the data collected by Kinect sensor v2



# 4. Contributing


- Provide the API function for users to use VAR Module as a web service


# 5. Author

-  Huynh The Thien
-  thienht@oslab.khu.ac.kr


# 6. License

Copyright [2016] [Huynh The Thien]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 


