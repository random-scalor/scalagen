@REM
@REM

@REM
@REM remove build artifacts on ci host
@REM

set "base=%~dp0\.."
set "name=com\carrotgarden\ns"
set "home_path=%HOME%\.m2\repository\%name%"
set "proj_path=%base%\test-repo\%name%"

echo home_path=%home_path%
echo proj_path=%proj_path%

if exist "%home_path%" ( rd /s /q "%home_path%" )
if exist "%proj_path%" ( rd /s /q "%proj_path%" )
