package cz.johnczek.kas.api.service;

import cz.johnczek.kas.api.response.BaseResponse;

public interface BaseService {

    /**
     * Method performs RLE, LZW and Huffman coding
     * @param input message to be processed
     * @return response holding results for every listed algorithm
     */
    BaseResponse baseMethod(String input);
}
