import React, { useEffect, useState } from "react";
import { CodeSnippet } from "../components/code-snippet";
import { PageLayout } from "../components/page-layout";
import { getBookACabProtected } from "../services/cap-booking.service";
import { useAuth0 } from "@auth0/auth0-react";

export const ProtectedPage = () => {
  const [message, setMessage] = useState("");
  const { getAccessTokenSilently } = useAuth0();

  useEffect(() => {
    let isMounted = true;

    const getMessage = async () => {
      // const { data, error } = await getProtectedResource();
      const accessToken = await getAccessTokenSilently();
      const { data, error } = await getBookACabProtected(accessToken);

      if (!isMounted) {
        return;
      }

      if (data) {
        // setMessage(JSON.stringify(data, null, 2));
        setMessage(data);
      }

      if (error) {
        setMessage(JSON.stringify(error, null, 2));
      }
    };

    void getMessage();

    return () => {
      isMounted = false;
    };
  }, [getAccessTokenSilently]);

  return (
    <PageLayout>
      <div className="content-layout">
        <h1 id="page-title" className="content__title">
          Protected Page
        </h1>
        <div className="content__body">
          <p id="page-description">
            <span>
              This page retrieves a <strong>protected message</strong> from an
              external API.
            </span>
            <span>
              <strong>Only authenticated users should access this page.</strong>
            </span>
          </p>
          <CodeSnippet title="Protected Message" code={message} />
        </div>
      </div>
    </PageLayout>
  );
};
