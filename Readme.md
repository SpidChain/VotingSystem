How to compile contracts
------------------------

   solc *.sol --bin --abi --optimize -o build
   cd build/
   mv ConvertLib.sol\:ConvertLib.abi ConvertLib.abi
   mv MetaCoin.sol\:MetaCoin.abi MetaCoin.abi
   web3j solidity generate MetaCoin.bin MetaCoin.abi -o ../../src/main/java/ -p io.positronicbrain.test_0
   web3j solidity generate ConvertLib.bin ConvertLib.abi -o ../../src/main/java/ -p io.positronicbrain.test_0
  
