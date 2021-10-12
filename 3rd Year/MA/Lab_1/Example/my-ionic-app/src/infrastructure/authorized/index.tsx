import { observer } from "mobx-react";
import { PropsWithChildren, useContext } from "react"
import { AuthorizedContext } from "./authorized-store"

const Authorized = ({ children }: PropsWithChildren<any>) => {
    const { isAuthorized } = useContext(AuthorizedContext);

    return isAuthorized ? children : null;
}

export default observer(Authorized);
