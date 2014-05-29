package com.github.ambry.shared;

import com.github.ambry.utils.Utils;

import java.io.DataInputStream;
import java.io.IOException;


/**
 * Response of delete request
 */
public class DeleteResponse extends Response {

  public DeleteResponse(int correlationId, String clientId, ServerErrorCode error) {
    super(RequestResponseType.DeleteResponse, Request_Response_Version, correlationId, clientId, error);
  }

  public static DeleteResponse readFrom(DataInputStream stream)
      throws IOException {
    RequestResponseType type = RequestResponseType.values()[stream.readShort()];
    if (type != RequestResponseType.DeleteResponse) {
      throw new IllegalArgumentException("The type of request response is not compatible");
    }
    Short versionId = stream.readShort();
    int correlationId = stream.readInt();
    String clientId = Utils.readIntString(stream);
    ServerErrorCode error = ServerErrorCode.values()[stream.readShort()];
    // ignore version for now
    return new DeleteResponse(correlationId, clientId, error);
  }
}