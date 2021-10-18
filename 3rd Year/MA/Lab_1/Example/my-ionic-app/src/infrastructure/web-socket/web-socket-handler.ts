import { BASE_WS_URL } from "../../accessors/constants";
import { getProtocol } from "../../accessors/helper-functions";
import { Change, ChangeType, WebSocketListener } from "./types";

const WebSocketHandler = async (
    createListener: WebSocketListener,
    updateListener: WebSocketListener,
    deleteListener: WebSocketListener
): Promise<() => void> => {
    const webSocket = new WebSocket(`${BASE_WS_URL}books/changes`, await getProtocol());

    const keepAlive = () => webSocket.send("");

    const mapListener = (type: ChangeType) => {
        switch (type) {
            case ChangeType.Create:
                return createListener;
            case ChangeType.Update:
                return updateListener;
            case ChangeType.Delete:
                return deleteListener;
        }
    }

    const callListener = ({ type, payload }: Change) => mapListener(type)(payload);

    webSocket.onmessage = ({ data }: MessageEvent<string>) => {
        keepAlive();
        callListener(JSON.parse(data) as Change);
    }

    return webSocket.close;
}

export default WebSocketHandler;
