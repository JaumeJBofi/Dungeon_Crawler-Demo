jar -cf Foundation.jar Foundation/*.class
jar -cf Controllers.jar Controllers/*.class
jar -cf Models.jar Models/*.class
jar -cmf manifest.txt Game.jar gameproject/*.class
@echo.
@echo Compilacion finalizada.
@echo.
@pause