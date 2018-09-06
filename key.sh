#!/bin/bash
java -cp ~/.m2/repository/org/jasypt/jasypt/1.9.2/jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input=$1 password=aezakmi algorithm=PBEWithMD5AndDES