@echo off
echo Cleaning up all .jar and .class files in subfolders...

:: Loop through all subfolders
for /d %%d in (*) do (
    echo Deleting .jar files in %%d...
    del /q "%%d\*.jar"
    echo Deleting .class files in %%d...
    del /q "%%d\*.class"
)

echo Cleanup complete.
pause
