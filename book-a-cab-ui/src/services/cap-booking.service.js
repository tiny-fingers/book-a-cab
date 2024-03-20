import { callExternalApi } from "./external-api.service";
import {isAuthorizationCodeError} from "@okta/okta-auth-js";

const apiServerUrl = process.env.REACT_APP_API_SERVER_URL;

export const getPublicResource = async () => {
  const config = {
    url: `${apiServerUrl}/api/messages/public`,
    // url: `/api/messages/public`,
    method: "GET",
    headers: {
      "content-type": "application/json",
    },
  };

  const { data, error } = await callExternalApi({ config });

  return {
    data: data || null,
    error,
  };
};

export const getProtectedResource = async (accessToken) => {
  const config = {
    // url: `/api/messages/protected`,
    url: `${apiServerUrl}/api/messages/protected`,
    method: "GET",
    headers: {
      "content-type": "application/json",
      Authorization: `Bearer ${accessToken}`,
    },
  };

  const { data, error } = await callExternalApi({ config });

  return {
    data: data || null,
    error,
  };
};

export const getAdminResource = async (accessToken) => {
  const config = {
    // url: `/api/messages/admin`,
    url: `${apiServerUrl}/api/messages/admin`,
    method: "GET",
    headers: {
      "content-type": "application/json",
      Authorization: `Bearer ${accessToken}`,
    },
  };

  const { data, error } = await callExternalApi({ config });

  return {
    data: data || null,
    error,
  };
};

export const getBookACabProtected = async (accessToken) => {
  const config = {
    // url: `/api/protected`,
    url: `${apiServerUrl}/api/protected`,
    method: "GET",
    headers: {
      "content-type": "application/json",
      Authorization: `Bearer ${accessToken}`,
    },
  };

  const { data, error } = await callExternalApi({ config });

  return {
    data: data || null,
    error,
  };
};

export const confirmBooking = async (accessToken, requestBody) => {
  const config = {
    // url: `/api/protected/confirm`,
    url: `${apiServerUrl}/api/protected/confirm`,
    method: "POST",
    headers: {
      "content-type": "application/json",
      Authorization: `Bearer ${accessToken}`,
    },
    data: JSON.stringify(requestBody),
  };

  const { data, error } = await callExternalApi({ config });

  console.log(`Received ${JSON.stringify(data)}`);

  return {
    data: data || null,
    error,
  };
};

export const sendEnquiry = async (requestBody) => {
    console.log(`Sending ${JSON.stringify(requestBody)}`);
    const config = {
        // url: `/api/public/enquire`,
        url: `${apiServerUrl}/api/public/enquire`,
        method: "GET",
        headers: {
            "content-type": "application/json",
        },
        params: {...requestBody},
    };

    const {data, error} = await callExternalApi({config});

    console.log(`Received ${JSON.stringify(data)}`);

    return {
        data: data || null,
        error,
    };
}

export const cancelBooking = async (accessToken, requestBody) => {
    console.log(`Sending ${JSON.stringify(requestBody)}`);
    const config = {
        // url: `/api/public/enquire`,
        url: `${apiServerUrl}/api/protected/cancelBooking`,
        method: "DELETE",
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
        params: {...requestBody},
    };

    const {data, error} = await callExternalApi({config});

    console.log(`Received ${JSON.stringify(data)}`);

    return {
        data: data || null,
        error,
    };
}

export const getAllBookings = async (accessToken) => {
    console.log(`Sending ${accessToken}`);

    const config = {
    // url: `/api/protected/bookings`,
    url: `${apiServerUrl}/api/protected/bookings`,
    method: "GET",
    headers: {
      "content-type": "application/json",
       Authorization: `Bearer ${accessToken}`,
    },
  };

  const { data, error } = await callExternalApi({ config });

  console.log(`Received ${JSON.stringify(data)}`);

  return {
    data: data || null,
    error,
  };
};
