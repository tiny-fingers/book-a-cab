import { callExternalApi } from "./external-api.service";

const apiServerUrl = process.env.REACT_APP_API_JSON_SERVER_URL;

export const getCurrentAddress = async () => {
  const config = {
    url: `${apiServerUrl}/api-geo-address-current-location`,
    method: "GET",
    headers: {
      "content-type": "application/json",
    },
  };

  const { data, error } = await callExternalApi({ config });

  return {
    data: data.data || null,
    error,
  };
};
