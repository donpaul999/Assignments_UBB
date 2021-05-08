@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  client startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and CLIENT_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\client.jar;%APP_HOME%\lib\common.jar;%APP_HOME%\lib\spring-webmvc-5.3.5.jar;%APP_HOME%\lib\spring-web-5.3.5.jar;%APP_HOME%\lib\postgresql-42.2.19.jar;%APP_HOME%\lib\spring-context-support-5.3.5.jar;%APP_HOME%\lib\spring-data-jpa-2.1.6.RELEASE.jar;%APP_HOME%\lib\spring-context-5.3.5.jar;%APP_HOME%\lib\lombok-1.18.18.jar;%APP_HOME%\lib\dom4j-1.6.1.jar;%APP_HOME%\lib\spring-orm-5.3.5.jar;%APP_HOME%\lib\hibernate-core-5.4.2.Final.jar;%APP_HOME%\lib\log4j-slf4j-impl-2.10.0.jar;%APP_HOME%\lib\spring-data-commons-2.1.6.RELEASE.jar;%APP_HOME%\lib\slf4j-api-1.8.0-alpha2.jar;%APP_HOME%\lib\log4j-1.2-api-2.10.0.jar;%APP_HOME%\lib\log4j-core-2.10.0.jar;%APP_HOME%\lib\reflections-0.9.10.jar;%APP_HOME%\lib\spring-aop-5.3.5.jar;%APP_HOME%\lib\spring-jdbc-5.3.5.jar;%APP_HOME%\lib\spring-tx-5.3.5.jar;%APP_HOME%\lib\spring-beans-5.3.5.jar;%APP_HOME%\lib\spring-expression-5.3.5.jar;%APP_HOME%\lib\spring-core-5.3.5.jar;%APP_HOME%\lib\checker-qual-3.5.0.jar;%APP_HOME%\lib\xml-apis-1.0.b2.jar;%APP_HOME%\lib\aspectjrt-1.9.2.jar;%APP_HOME%\lib\hibernate-commons-annotations-5.1.0.Final.jar;%APP_HOME%\lib\jboss-logging-3.3.2.Final.jar;%APP_HOME%\lib\javax.persistence-api-2.2.jar;%APP_HOME%\lib\javassist-3.24.0-GA.jar;%APP_HOME%\lib\byte-buddy-1.9.10.jar;%APP_HOME%\lib\antlr-2.7.7.jar;%APP_HOME%\lib\jboss-transaction-api_1.2_spec-1.1.1.Final.jar;%APP_HOME%\lib\jandex-2.0.5.Final.jar;%APP_HOME%\lib\classmate-1.3.4.jar;%APP_HOME%\lib\jaxb-runtime-2.3.1.jar;%APP_HOME%\lib\jaxb-api-2.3.1.jar;%APP_HOME%\lib\javax.activation-api-1.2.0.jar;%APP_HOME%\lib\dom4j-2.1.1.jar;%APP_HOME%\lib\log4j-api-2.10.0.jar;%APP_HOME%\lib\guava-18.0.jar;%APP_HOME%\lib\annotations-2.0.1.jar;%APP_HOME%\lib\spring-jcl-5.3.5.jar;%APP_HOME%\lib\txw2-2.3.1.jar;%APP_HOME%\lib\istack-commons-runtime-3.0.7.jar;%APP_HOME%\lib\stax-ex-1.8.jar;%APP_HOME%\lib\FastInfoset-1.2.15.jar

@rem Execute client
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %CLIENT_OPTS%  -classpath "%CLASSPATH%" Main %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable CLIENT_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%CLIENT_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
