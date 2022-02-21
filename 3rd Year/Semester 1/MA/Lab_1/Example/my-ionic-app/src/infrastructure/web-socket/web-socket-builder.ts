
import { WebSocketListener } from "./types";
import WebSocketHandler from "./web-socket-handler";

export class WebSocketBuilder {
    private createListener: WebSocketListener = () => {};
    private updateListener: WebSocketListener = () => {};
    private deleteListener: WebSocketListener = () => {};

    public onCreate = (listener: WebSocketListener) => {
        this.createListener = listener;

        return this;
    }

    public onUpdate = (listener: WebSocketListener) => {
        this.updateListener = listener;

        return this;
    }

    public onDelete = (listener: WebSocketListener) => {
        this.deleteListener = listener;

        return this;
    }

    public connect = () => WebSocketHandler(
        this.createListener,
        this.updateListener,
        this.deleteListener
    );
}

const BuildWebSocket = () => new WebSocketBuilder();

export default BuildWebSocket;
