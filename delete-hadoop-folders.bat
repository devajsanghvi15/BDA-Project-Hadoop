@echo off

REM List of Hadoop directories to delete
set DIRS=/query1 /query2 /query3 /query4 /query5 /query6 /query7 /query8

echo Deleting Hadoop folders...

for %%D in (%DIRS%) do (
    echo Deleting %%D ...
    hadoop fs -rm -r %%D
    if errorlevel 1 (
        echo Failed to delete %%D. It might not exist.
    ) else (
        echo Successfully deleted %%D.
    )
)

echo Hadoop folders deletion complete.
pause
