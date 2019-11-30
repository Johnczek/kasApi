# Java spring based api for Data Compression and Cryptology subject school project

## API takes POST request on /api endpoint with the JSON with only one string property called `message`
Example 
`curl -d '{"message":"value"}' -H "Content-Type: application/json" -X POST localhost:8080/kas`

## API takes the message and pass them to these algorithms: Run length encoding, Huffman coding and LZW. Result is set of json object, every one respresents result of comrimation of one of previouslz mentioned algorithms containing informations as encoded/decoded message, sizes of these messages, time needed to perform algoritms etc.
