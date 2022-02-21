import { observer } from "mobx-react";
import { useContext, useEffect } from "react"
import { AuthorizedContext } from "./authorized-store"

interface Props {
    authorized?: any;
    notAuthorized?: any;
}

const AuthorizedView = ({ authorized, notAuthorized }: Props) => {
    const { isAuthorized, initialize } = useContext(AuthorizedContext);

    useEffect(() => {
        initialize();
    }, [initialize]);

    if (isAuthorized() === undefined) {
        return null;
    }

    return isAuthorized() ? authorized : notAuthorized;
}

export default observer(AuthorizedView);
